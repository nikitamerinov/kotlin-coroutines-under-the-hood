import {Layout, makeScene2D, Node, Txt} from '@motion-canvas/2d';
import {createRef, createRefArray, Direction, slideTransition, waitFor, waitUntil} from '@motion-canvas/core';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();
  const titleWrapper = createRef<Node>();
  const textLines = createRefArray<Txt>()

  view.add(
    <Layout layout size={'100%'} padding={[90, 120, 200]} direction={'column'} gap={100} alignItems={'center'}>
      <Node ref={titleWrapper}>
        <Caption ref={title} text={'Introduction to the series'} />
      </Node>
      <Layout direction={'column'} grow={1} justifyContent={'space-around'}>
        <Text ref={textLines} text={'What topics will be covered'} />
        <Text ref={textLines} text={'What will not be covered'} />
        <Text ref={textLines} text={'Additional notes'} />
      </Layout>
    </Layout>
  );

  prepare(titleWrapper(), title(), textLines);

  yield* slideTransition(Direction.Right);

  yield* waitUntil('start');

  yield* titleWrapper().y(0, 1.5);

  for (const textLine of textLines) {
    const text = textLine.text();
    textLine.size(textLine.size());
    textLine.text('');
    textLine.opacity(1);
    yield textLine.text(text, 1);
    yield* waitFor(0.5);
  }

  yield* waitUntil('end');
});

function prepare(titleWrapper: Node, title: Txt, textLines: Txt[]) {
  const titleDeltaFromCenter = title.position();
  titleWrapper.position.add(titleDeltaFromCenter.flipped);

  for (const textLine of textLines) {
    textLine.opacity(0);
  }
}
