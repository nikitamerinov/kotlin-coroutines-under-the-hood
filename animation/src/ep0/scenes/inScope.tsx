import {Layout, makeScene2D, Txt} from '@motion-canvas/2d';
import {createRef, createRefArray, DEFAULT, Direction, slideTransition, waitFor, waitUntil} from '@motion-canvas/core';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();
  const textLines = createRefArray<Txt>()

  view.add(
    <>
      <Caption ref={title} text={'In scope'} />
      {/*<Layout layout size={'100%'} padding={150} direction={'column'} gap={100} alignItems={'center'}*/}
      {/*        textWrap={true}>*/}
        <Layout layout direction={'column'} gap={120} textWrap={'pre'}>
          <Text ref={textLines} text={'How the compiler transforms suspend functions in\nthe bytecode'} opacity={0}/>
          <Text ref={textLines} opacity={0}
                text={'How synchronous computations are implemented with\n' +
                  'coroutines:\niterator/sequence builder, DeepRecursiveFunction'} />
          <Text ref={textLines} opacity={0}
                text={'How coroutine state is tracked with DebugProbes'}
          />
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
    yield textLine.text(text, 1.5);
  }

  yield* waitUntil('switch text');

  yield textLines[0].text('', 1);
  yield textLines[1].text('', 1);
  yield* textLines[2].text('', 1);
  textLines[0].size(DEFAULT);
  textLines[1].size(DEFAULT);
  textLines[2].size(DEFAULT);
  textLines[0].text('How launch, async, runBlocking are implemented');
  textLines[1].text('How structured concurrency and coroutine\ncancellation work');
  textLines[2].text('How channel and flow are implemented');
  textLines[0].size(textLines[0].size());
  textLines[1].size(textLines[1].size());
  textLines[2].size(textLines[2].size());
  textLines[0].text('');
  textLines[1].text('');
  textLines[2].text('');
  yield textLines[0].text('How launch, async, runBlocking are implemented', 1);
  yield textLines[1].text('How structured concurrency and coroutine\ncancellation work', 1);
  yield* textLines[2].text('How channel and flow are implemented', 1);

  yield* waitUntil('end');
});
