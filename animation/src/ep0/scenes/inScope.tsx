import {makeScene2D, Txt, Code, CODE, Layout} from '@motion-canvas/2d';
import {createRef, Direction, Origin, slideTransition, waitFor, waitUntil} from '@motion-canvas/core';
import {Caption, Text} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();

  const text = `
- How launch, async, runBlocking are implemented?
- How coroutine state is tracked with DebugProbes?
- How coroutine context switching is done?
- How coroutine cancellation works?
- ... 
- And much more`;

  const t1 = createRef<Txt>();

  view.add(
    <Layout layout size={'100%'} padding={[90, 120, 200]} direction={'column'} gap={100} alignItems={'center'}
            textWrap={true}>
      <Caption ref={title} text={'In scope'} />
      <Layout direction={'column'} justifyContent={'space-between'} height={'100%'}>
        <Text ref={t1} text={'How the compiler transforms suspend functions in the bytecode'} />
        <Text
          text={'How synchronous suspendable computations are implemented: iterator/sequence builders, DeepRecursiveFunction'} />
        <Text
          text={'How coroutines are used in a synchronous context: iterator/sequence builders, DeepRecursiveFunction'}
        />
      </Layout>
    </Layout>
  );

  yield* slideTransition(Direction.Right);

  // t1().size(t1().size());
  yield* t1().text('asd', 1);

  yield* waitUntil('end');
});
