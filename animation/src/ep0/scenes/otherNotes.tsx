import {makeScene2D, Layout, Txt} from '@motion-canvas/2d';
import {createRef, createRefArray, Direction, slideTransition, waitFor, waitUntil} from '@motion-canvas/core';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();
  const textLines = createRefArray<Txt>();

  view.add(
    <>
      <Caption ref={title} text={'Other notes'} />
    {/*<Layout layout size={'100%'} padding={[90, 120, 200]} direction={'column'} gap={100} alignItems={'center'}*/}
    {/*        textWrap={true}>*/}
      <Layout layout direction={'column'} gap={200}>
        <Text ref={textLines} text={'Target platform: JVM'} opacity={0}/>
        <Text ref={textLines} text={'Learning by reimplementing'} opacity={0}/>
      </Layout>
    {/*</Layout>*/}
    </>
  );

  yield* slideTransition(Direction.Right);

  yield* waitUntil('start');

  title().size(title().size());
  yield* title().text('', 0.5);

  for (const textLine of textLines) {
    const text = textLine.text();
    textLine.size(textLine.size());
    textLine.text('');
    textLine.opacity(1);
    yield textLine.text(text, 1);
  }

  yield* waitUntil('end');
});
